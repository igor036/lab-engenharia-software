/**
 * Author: Igor Joaquim dos Santos Lima
 * Data: 15/08/2019
 */
package com.linecode.configuracao.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.linecode.configuracao.seguranca.enumerador.ApiPublicaEnumerador;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SegurancaWebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer  {

    @Autowired
    private Environment env;

    /**
     * 
     * Configuracao de chamadas a API
     * 
     * 1- Configura as rotas presentes em {com.example.aplicacao.configuracao.seguranca.enumerador} como rotas públicas.
     * 
     * 2 - Adicionar um filtro para requisicoes a pagina de login para autenticação com JWT
     * 
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeRequests().antMatchers(ApiPublicaEnumerador.CADASTRO.getUrlApi())
                .permitAll().antMatchers(HttpMethod.POST, ApiPublicaEnumerador.LOGIN.getUrlApi()).permitAll()
                .anyRequest().authenticated().and()

                .addFilterBefore(new FiltroLoginJWT(ApiPublicaEnumerador.LOGIN.getUrlApi(), getApplicationContext()),
                        UsernamePasswordAuthenticationFilter.class)

                .addFilterBefore(new FiltroAutenticacaoJWT(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Adiciona um usuário padrão (ADMIN) na memória.
     * 
     * @param {@link AuthenticationManagerBuilder} autenticacao
     * @throws @{@link Exception}
     * 
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(env.getProperty("usuario.admin.email"))
                .password(env.getProperty("usuario.admin.senha")).roles(env.getProperty("usuario.admin.perfil"));
    }
    
    /**
     * Configuração de politica cross
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowedHeaders("*");
    }
}
