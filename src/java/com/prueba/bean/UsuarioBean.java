/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.bean;

import com.prueba.dao.UsuarioDao;
import com.prueba.model.Usuario;
import java.io.Serializable;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;



/**
 *
 * @author Yeison
 */

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable{
    private Usuario usuario = new Usuario();
    private List<Usuario> listaUsuario;
    private String accion;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    

    
    public String iniciarSesion() throws Exception{
        String redireccion = null;
        UsuarioDao dao;
        try{
            dao = new UsuarioDao();
            Usuario u = dao.leerID(usuario);
            if(u !=null){
                this.usuario = u;
                String ru = u.getRolUsuario();
                switch(ru){
                    case "Administrador":
                        redireccion = "admin?faces-redirect=true";
                        break;
                    case "Editor":
                        redireccion = "editor?faces-redirect=true";
                        break;
                    case "Eliminador":
                        redireccion = "eliminador?faces-redirect=true";                          
                        break;           
                }
               
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales Incorrectas"));
            }
        }catch(Exception e){  
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
            throw e;
    }
        return redireccion;
    }
    
    public String cerrarSesion(){
        String redireccion = "index?faces-redirect=true";
        this.limpiar();
        return redireccion;
    }
    
    public void operar() throws Exception{
    switch(accion){
        case "Registrar":
            this.registrar();
            this.limpiar();
            break;
        case "Modificar":
            this.modificar();
            this.limpiar();
            break;
    }
    }
    
    public void limpiar(){
    this.usuario.setIdUsuario(0);
    this.usuario.setLoginUsuario("");
    this.usuario.setPasswordUsuario("");
    this.usuario.setRolUsuario("");
    }
    
    public String registrar() throws Exception {
    UsuarioDao dao;
    String direc = null;
    try{
        dao = new UsuarioDao();
        boolean reg = dao.registrar(usuario);
        if(reg == true){
            direc = "index?faces-redirect=true";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
        }
        //this.listar();
    }catch(Exception e){  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Error al Registrar"));
        throw e;
    }   
    return direc;
    }
    
    public void modificar() throws Exception {
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        dao.modificar(usuario);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void listar() throws Exception{
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        listaUsuario = dao.listar();
    
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Usuario usu) throws Exception{
            this.usuario = usu; 
            this.accion = "Modificar";
    }
    

   
    public void eliminar(Usuario usu) throws Exception {
    UsuarioDao dao;
    try{
        dao = new UsuarioDao();
        dao.eliminar(usu);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    

}
