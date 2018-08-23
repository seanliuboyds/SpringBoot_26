package com.example.demo;

import jdk.management.resource.internal.ApproverGroup;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<AppRole, Long>{
    AppRole findByRole(String role);
}
