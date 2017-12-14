package com.marck.spring.app.Models.ClienteDao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.marck.spring.app.Models.Entity.Cliente;

@Repository
public class CimplOther implements ICliente{
	
	private final String SelectAll="Select * from Clientes";
	private final String SelectOne="Select * from Clientes where id=?";
	private final String Insert="INSERT INTO Clientes(nombre,apellido,email,fecha) values(?,?,?,?)";
	private final String Update="Update Clientes SET nombre=?;pellido=?,email=?,fecha=? where id=?";
	private final String Delete="DELETE from Clientes where id=?";
	
	@Autowired
	private DataSource dataSource;

    public void setDataSource(DataSource ds) {
        dataSource = ds;
    }

	@Override
	public List<Cliente> findAll() {
		JdbcTemplate select= new JdbcTemplate(dataSource);
		System.out.println("Todos los Clientes Seleccionados");
		return select.query(SelectAll, new ClienteRow());
	}

	@Override
	public Cliente findOne(Long id) {
		JdbcTemplate selectone=new JdbcTemplate(dataSource);
		Cliente c= new Cliente();
		c.setId(id);
		Object o;
		o=selectone.queryForObject(SelectOne, new ClienteRow(),id);
		return c;
	}

	@Override
	public int insertCliente(Cliente cliente) {
		JdbcTemplate insert=new JdbcTemplate(dataSource);
		return insert.update(Insert, cliente);
	}

	@Override
	public int deleteCliente(Long id) {
		JdbcTemplate delete= new JdbcTemplate(dataSource);
		return delete.update(Delete,id);
	}

	@Override
	public int updateCliente(Cliente cliente) {
		JdbcTemplate insert=new JdbcTemplate(dataSource);
		return insert.update(Update, cliente);
	}

}
