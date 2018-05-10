/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba.dao;

import com.prueba.model.Equipo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison
 */
public class EquipoDao extends Dao{
    
    public void registrar(Equipo equ) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO equipo (nombreEquipo) values(?)");
            st.setString(1, equ.getNombreEquipo());           
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
        
    public List<Equipo> listar() throws Exception{
            List<Equipo> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT idEquipo, nombreEquipo FROM equipo");
                rs = st.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Equipo equi = new Equipo();
                    equi.setIdEquipo(rs.getInt("idEquipo"));
                    equi.setNombreEquipo(rs.getString("nombreEquipo"));
                    
                    lista.add(equi);
                
                }
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Equipo leerID(Equipo per) throws Exception{
        Equipo pers = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idEquipo, nombreEquipo FROM equipo WHERE idEquipo = ?");
                st.setInt(1, per.getIdEquipo());
                rs = st.executeQuery();
                while(rs.next()){
                    pers = new Equipo();
                    pers.setIdEquipo(rs.getInt("idEquipo"));
                    pers.setNombreEquipo(rs.getString("nombreEquipo"));
                    
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return pers;
    }
    
    public void modificar(Equipo equi) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE equipo SET nombreEquipo = ? WHERE idEquipo = ?");
            st.setString(1, equi.getNombreEquipo());                      
            st.setInt(2, equi.getIdEquipo());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Equipo equi) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM equipo  WHERE idEquipo = ?");
            st.setInt(1, equi.getIdEquipo());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
}
