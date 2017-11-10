package br.com.azulseguros.restpiloto.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.azulseguros.restpiloto.resources.ProjetoResource;

@ApplicationPath("/")
public class PilotoApplication extends Application{
    private Set<Object> singletons = new HashSet<Object>();
    
    public void RestEasyServices() {
        singletons.add(new ProjetoResource());
    }
 
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
