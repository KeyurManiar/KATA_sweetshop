package com.sweetshop.controller;

import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {
    private final SweetRepository repo;

    public SweetController(SweetRepository repo){this.repo = repo;}

    @PostMapping
    public Sweet add(@RequestBody Sweet s){ return repo.save(s); }

    @GetMapping
    public List<Sweet> all(){ return repo.findAll(); }

    @GetMapping("/search")
    public List<Sweet> search(@RequestParam(required=false) String name,
                              @RequestParam(required=false) String category,
                              @RequestParam(required=false) Double minPrice,
                              @RequestParam(required=false) Double maxPrice){
        if(name!=null) return repo.findByNameContainingIgnoreCase(name);
        if(category!=null) return repo.findByCategoryIgnoreCase(category);
        List<Sweet> all = repo.findAll();
        double min = minPrice==null?Double.MIN_VALUE:minPrice;
        double max = maxPrice==null?Double.MAX_VALUE:maxPrice;
        return all.stream().filter(s->s.getPrice()>=min && s.getPrice()<=max).toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Sweet s){
        Optional<Sweet> o = repo.findById(id);
        if(o.isEmpty()) return ResponseEntity.notFound().build();
        Sweet ex = o.get();
        ex.setName(s.getName()); ex.setCategory(s.getCategory()); ex.setPrice(s.getPrice()); ex.setQuantity(s.getQuantity());
        repo.save(ex);
        return ResponseEntity.ok(ex);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<?> purchase(@PathVariable Long id, @RequestParam(defaultValue="1") int qty){
        Optional<Sweet> o = repo.findById(id);
        if(o.isEmpty()) return ResponseEntity.notFound().build();
        Sweet s = o.get();
        if(s.getQuantity() < qty) return ResponseEntity.badRequest().body("insufficient stock");
        s.setQuantity(s.getQuantity()-qty);
        repo.save(s);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/{id}/restock")
    public ResponseEntity<?> restock(@PathVariable Long id, @RequestParam(defaultValue="1") int qty){
        Optional<Sweet> o = repo.findById(id);
        if(o.isEmpty()) return ResponseEntity.notFound().build();
        Sweet s = o.get();
        s.setQuantity(s.getQuantity()+qty);
        repo.save(s);
        return ResponseEntity.ok(s);
    }
}
