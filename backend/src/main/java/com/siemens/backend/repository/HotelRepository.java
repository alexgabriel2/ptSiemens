package com.siemens.backend.repository;

import com.siemens.backend.entity.Hotel;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
