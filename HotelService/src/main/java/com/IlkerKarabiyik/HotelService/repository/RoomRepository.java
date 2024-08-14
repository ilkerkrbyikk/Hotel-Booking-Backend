package com.IlkerKarabiyik.HotelService.repository;

import com.IlkerKarabiyik.HotelService.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Page<Room> findByAvailable(boolean available, Pageable pageable);
}
