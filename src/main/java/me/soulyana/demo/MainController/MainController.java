package me.soulyana.demo.MainController;

import me.soulyana.demo.Model.Pet;
import me.soulyana.demo.Repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    PetRepository petRepository;

    @RequestMapping("/")
    public String listPets(Model model) {
        model.addAttribute("pets", petRepository.findAll());
        return "list";
    }

    @RequestMapping("/add")
    public String petForm(Model model) {
        model.addAttribute("pet", new Pet());
        return "petform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Pet pet, BindingResult result) {
        if (result.hasErrors()) {
            return "petform";
        }
        if(pet.getImage().isEmpty()) {
            pet.setImage("/Images/lost.jpg");
        }
        petRepository.save(pet);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String showPet(@PathVariable("id") long id, Model model) {
        //NEED .GET FOR SPRING 2
        model.addAttribute("pet", petRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updatePet(@PathVariable("id") long id, Model model) {
        //NEED .GET FOR SPRING 2
        model.addAttribute("pet", petRepository.findById(id).get());
        return "petform";
    }

    @RequestMapping("/delete/{id}")
    public String delPet(@PathVariable("id") long id) {
        petRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/reportFound/{id}")
    public String reportFound(@PathVariable("id") long id, Model model) {
        //NEED .GET FOR SPRING 2
        Pet pet = petRepository.findById(id).get();
        pet.setFound(false);
        petRepository.save(pet);
        model.addAttribute("pet", petRepository.findById(id).get());
        return "redirect:/";
    }

    @RequestMapping("/reportLost/{id}")
    public String reportLost(@PathVariable("id") long id, Model model) {
        //NEED .GET FOR SPRING 2
        Pet pet = petRepository.findById(id).get();
        pet.setFound(true);
        petRepository.save(pet);
        model.addAttribute("pet", petRepository.findById(id).get());
        return "redirect:/";
    }

}

