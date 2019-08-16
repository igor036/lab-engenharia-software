package com.linecode.configuracao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextoAplicacao implements ApplicationContextAware {

    private static ApplicationContext contexto;
    
    @Override
    public void setApplicationContext(ApplicationContext contextoAplicacao)  {
        contexto = contextoAplicacao;   
    }

    public static ApplicationContext getContext() {
        return contexto;
    }

}
