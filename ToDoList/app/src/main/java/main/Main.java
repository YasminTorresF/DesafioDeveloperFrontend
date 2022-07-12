/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package main;

import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.connectionFactory;


public class Main {
    
    public static void main(String[] args) {
        
        //criar um novo projeto
        ProjectController projectController = new ProjectController();
        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("descri��o");
        projectController.save(project);
      
        //Mudificar um projeto
//      ProjectController projectController = new ProjectController();
//       Project project = new Project();
//       project.setId(2);
//       project.setName("Novo nome do projeto");
//       project.setDescription("descri��o");

        //Listar projetos
//       projectController.update(project);        
//        List<Project>projects = projectController.getAll();
//        System.out.print("total de projetos = " + projects.size());
        
        //Remover projeto
//        projectController.removeById(2);
        
        
    }
}
