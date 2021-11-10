package com.emergentes.dao;
import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvisoDAO1 extends ConexionDB implements AvisoDAO {

    @Override
    public void insert(Seminario seminarios) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO seminario (titulo, expositor, fecha, hora, cupo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, seminarios.getTitulo());
            ps.setString(2, seminarios.getExpositor());
            ps.setString(3, seminarios.getFecha());
            ps.setString(4, seminarios.getHora());
            ps.setInt(5, seminarios.getCupo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Seminario seminario) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE seminario SET titulo=?, expositor=?, fecha=?, hora=?, cupo=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, seminario.getTitulo());
            ps.setString(2, seminario.getExpositor());
            ps.setString(3, seminario.getFecha());
            ps.setString(4, seminario.getHora());
            ps.setInt(5, seminario.getCupo());
            ps.setInt(6, seminario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE from seminarios where id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Seminario getById(int id) throws Exception {
        Seminario sem = new Seminario();
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM seminario where id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                sem.setId(rs.getInt("id"));
                sem.setTitulo(rs.getString("titulo"));
                sem.setExpositor(rs.getString("expositor"));
                sem.setFecha(rs.getString("fecha"));
                sem.setHora(rs.getString("hora"));
                sem.setCupo(rs.getInt("cupo"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return sem;
    }

    @Override
    public List<Seminario> getAll() throws Exception {
        List<Seminario> lista = null;
        try {
            this.conectar();
            String sql = "SELECT * FROM seminarios";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Seminario>();

            while (rs.next()) {
                Seminario sem = new Seminario();
                
                sem.setId(rs.getInt("id"));
                sem.setTitulo(rs.getString("titulo"));
                sem.setExpositor(rs.getString("expositor"));
                sem.setFecha(rs.getString("fecha"));
                sem.setHora(rs.getString("hora"));
                sem.setCupo(rs.getInt("cupo"));

                lista.add(sem);
            }
            rs.close();;
            ps.close();;

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
