/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.connectionFactory;

/**
 *
 * @author Yasmin Torres
 */
public class ProjectController {
    
    public void save(Project project){
    
        String sql = "INSERT INTO projects ( name, description, createdAt, updatedAt) "
                + "VALUES(?, ?, ?, ? )";
        
        //criação das variáveis
        Connection connection = null;
        PreparedStatement statement = null;
        
            try{
                //criação da conexão com o banco de dados
                connection = connectionFactory.getConnection();
                //preparando a query
                statement = connection.prepareStatement(sql);
                //setando os valores                
                statement.setString(1, project.getName());
                statement.setString(2, project.getDescription());
                statement.setDate(3, new Date(project.getCreatedAt().getTime()));
                statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
                //executando a query
                statement.execute();
                                
            }catch(Exception ex){
                throw new RuntimeException("Erro ao salvar o projeto:" + ex.getMessage(), ex);
            }finally{
                connectionFactory.closeConnection(connection, statement);
            }
        
    }
    
    public void update(Project project){
    
        String sql = "UPDATE projects SET name = ?, description = ?, createdAt = ?, updatedAt = ? WHERE id = ?";
        
        //criação das variáveis
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //criação da conexão com o banco de dados
            connection = connectionFactory.getConnection();
            //preparando a query
            statement = connection.prepareStatement(sql);
            //setando os valores            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            //executando a query
            statement.execute();
        }catch(Exception ex){
            throw new RuntimeException("Erro ao atualizar o projeto" + ex.getMessage(), ex);
            
        }
    }
    
    public void removeById(int idProject){
        String sql = "DELETE FROM projects WHERE id = ?";
        
        //criação das variáveis
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            //criação da conexão com o banco de dados
            connection = connectionFactory.getConnection();
            //preparando a query
            statement = connection.prepareStatement(sql);
           //setando os valores
            statement.setInt(1, idProject);
            //executando a query
            statement.execute();
            
        } catch(Exception ex) {
            throw new RuntimeException("Erro ao deletar o projeto" + ex.getMessage(), ex);
            
        } finally{
            connectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Project> getAll(){
        
        String sql = "SELECT * FROM projects";
                       
        //lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Project> projects = new ArrayList<>();
        
        //criação das variáveis
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try{
            //criação da conexão com o banco de dados
            connection = connectionFactory.getConnection();
            //preparando a query
            statement = connection.prepareStatement(sql);
            
            //valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //enquanto houverem valores a serem percorridos no resultSet
            while(resultSet.next()){
                
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));               
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));                
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                projects.add(project);
            }
        }catch(Exception ex) {
            throw new RuntimeException("Erro ao buscar o projeto" + ex.getMessage(), ex);
            
        } finally{
            connectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //lista de tarefas que foi criada e carregada do banco de dados
        return projects;
    }
    
        
}
