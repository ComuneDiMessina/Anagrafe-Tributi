package it.almaviva.impleme.tributi.anagrafe.permission;

import it.almaviva.eai.ljsa.sdk.core.security.LjsaUser;
import it.almaviva.eai.pm.core.grpc.Group;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Aspect
@Component
public class AllowedValueChecker {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) "
            + "|| @annotation(*.GetMapping)"
            + "|| @annotation(*.PostMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PatchMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.PutMapping)"
            + "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature mSignature = (MethodSignature) joinPoint.getSignature();
        List<Parameter> params = Arrays.asList(mSignature.getMethod().getParameters());
        Object[] args = joinPoint.getArgs();

        IntStream.range(0, params.size()).mapToObj((i) -> new ParameterWithvalue(params.get(i), args[i]))
                .filter(param -> null != param.parameter.getAnnotation(AllowedInDomain.class)).forEach((param) -> {
                    AllowedInDomain ann = param.parameter.getAnnotation(AllowedInDomain.class);
                    check(ann, param.value);
                });

        return joinPoint.proceed();
    }

    <T> void check(AllowedInDomain annotation, T value) {

        LjsaUser ljsaUser = (LjsaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final Group group = (Group) ljsaUser.getGroups().stream().findFirst().get();
        Group.DomainValue dv = group.getDomainvaluesList().stream()
                .filter(domainValue -> domainValue.getDomain().getName().equals(annotation.value())).findFirst().get();

        if (!dv.getValue().equals(value)) {
            throw new RuntimeException("Errore nella selezione dell'ente");
        }

    }

}

class ParameterWithvalue {
    public Parameter parameter;
    public Object value;

    public ParameterWithvalue(Parameter parameter, Object value) {
        this.parameter = parameter;
        this.value = value;
    }

}
