/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import VO.Reporte1VO;
import VO.Reporte4VO;
import VO.libroVO;
import connexion.connexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author undercoder
 */
public class ConsultasDAO {

    public ConsultasDAO() {

    }

    public LinkedList consulta1(String nombre) {
        connexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new connexion();
            select = cn.getConnection().prepareStatement("SELECT autor.nombre as Autor,libro.titulo as Titulo FROM autor,libro,libaut WHERE autor.id = libaut.id_autor AND libaut.id = libro.id and libro.titulo "
                    + "like '%" + nombre + "%'");
            rs = select.executeQuery();
            while (rs.next()) {
                Reporte1VO consulta = new Reporte1VO();
                consulta.setTitulo(rs.getString("titulo"));
                consulta.setAutor(rs.getString("autor"));
                datos.add(consulta);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarlibro(int id, String titulo, String editorial, String area) {
        connexion cn = null;
        PreparedStatement insert = null;
        try {
            cn = new connexion();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO public.libro(id, titulo, editorial, area) VALUES (?, ?, ?, ?);");
            insert.setInt(1, id);
            insert.setString(2, titulo);
            insert.setString(3, editorial);
            insert.setString(4, area);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public boolean eliminarLibro(int id_libro) {
        connexion cn = null;
        PreparedStatement delete = null;
        try {
            cn = new connexion();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM libro WHERE id = ?");
            delete.setInt(1, id_libro);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public LinkedList imprimir() {
        connexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new connexion();
            select = cn.getConnection().prepareStatement("SELECT * from libro;");
            rs = select.executeQuery();
            while (rs.next()) {
                libroVO consulta = new libroVO();
                consulta.setid(rs.getInt("id"));
                consulta.settitulo(rs.getString("titulo"));
                consulta.seteditorial(rs.getString("editorial"));
                consulta.setarea(rs.getString("area"));
                datos.add(consulta);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consulta3(String nombre) {
        connexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new connexion();
            select = cn.getConnection().prepareStatement("SELECT * FROM LIBRO WHERE titulo "
                    + "like '%" + nombre + "%'");
            rs = select.executeQuery();
            while (rs.next()) {
                libroVO consulta = new libroVO();
                consulta.setid(rs.getInt("id"));
                consulta.settitulo(rs.getString("titulo"));
                consulta.seteditorial(rs.getString("editorial"));
                consulta.setarea(rs.getString("area"));
                datos.add(consulta);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consulta4(String nombre) {
        connexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new connexion();
            select = cn.getConnection().prepareStatement("SELECT A.nombre AS Autor, L.* FROM AUTOR A, LIBRO L, LIBAUT AL WHERE A.id = AL.id_autor AND AL.id = L.id AND A.nombre "
                    + "like '%" + nombre + "%'");
            rs = select.executeQuery();
            while (rs.next()) {
                Reporte4VO consulta = new Reporte4VO();
                consulta.setAutor(rs.getString("autor"));
                consulta.setId(rs.getInt("id"));
                consulta.setTitulo(rs.getString("titulo"));
                consulta.setEditorial(rs.getString("editorial"));
                consulta.setArea(rs.getString("area"));
                datos.add(consulta);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList imprimir2() {
        connexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new connexion();
            select = cn.getConnection().prepareStatement("SELECT A.nombre AS Autor, L.* FROM AUTOR A, LIBRO L, LIBAUT AL WHERE A.id = AL.id_autor AND AL.id = L.id");
            rs = select.executeQuery();
            while (rs.next()) {
                Reporte4VO consulta = new Reporte4VO();
                consulta.setAutor(rs.getString("autor"));
                consulta.setId(rs.getInt("id"));
                consulta.setTitulo(rs.getString("titulo"));
                consulta.setEditorial(rs.getString("editorial"));
                consulta.setArea(rs.getString("area"));
                datos.add(consulta);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

}
//SELECT autor.nombre as Autor,libro.titulo as Titulo FROM autor,libro,libaut WHERE autor.id = libaut.id_autor AND libaut.id = libro.id and libro.titulo like '%Don Quijote de la Mancha%';
