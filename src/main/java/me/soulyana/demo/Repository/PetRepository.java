package me.soulyana.demo.Repository;

import me.soulyana.demo.Model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long>{
}
