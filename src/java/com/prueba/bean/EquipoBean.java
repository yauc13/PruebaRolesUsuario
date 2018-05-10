/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.bean;

import com.prueba.dao.EquipoDao;
import com.prueba.model.Equipo;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeison
 */

@ManagedBean
@ViewScoped
public class EquipoBean implements Serializable{
    private Equipo equipo = new Equipo();
    private List<Equipo> listaEquipo;
    private String accion;

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.limpiar();
        this.accion = accion;
    }
    
    

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }
    
    private boolean isPostBack(){
        boolean rta;
        rta= FacesContext.getCurrentInstance().isPostback();
        return rta;
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
    this.equipo.setIdEquipo(0);
    this.equipo.setNombreEquipo("");
    }
    
    public void registrar() throws Exception {
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        dao.registrar(equipo);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void modificar() throws Exception {
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        dao.modificar(equipo);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
    public void listarInicio() throws Exception{
    EquipoDao dao;
    try{
        if(this.isPostBack() == false){
        dao = new EquipoDao();
        listaEquipo = dao.listar();
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    public void listar() throws Exception{
    EquipoDao dao;
    try{
        if(this.isPostBack() == true){
        dao = new EquipoDao();
        listaEquipo = dao.listar();
        }
    }catch(Exception e){   
        throw e;
    }
    }
    
    
    public void leerID (Equipo equi) throws Exception{
            this.equipo = equi; 
            this.accion = "Modificar";
    }
    

   
    public void eliminar(Equipo equi) throws Exception {
    EquipoDao dao;
    try{
        dao = new EquipoDao();
        dao.eliminar(equi);
        this.listar();
    }catch(Exception e){  
        throw e;
    }   
    }
    
}
