package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.ProjectModel;

public interface ProjectRepository extends JpaRepository<ProjectModel, Long>
{
    @Query("SELECT p FROM ProjectModel p JOIN ProjectMemberModel pm ON pm.project.id_project = p.id_project WHERE pm.user.id_user = :id_user")
    public List<ProjectModel> findByUser(@Param("id_user") Long id_user);
}