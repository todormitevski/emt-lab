package mk.ukim.finki.web;

import mk.ukim.finki.model.Country;
import mk.ukim.finki.model.dto.CountryDto;
import mk.ukim.finki.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> findAll(){
        return countryService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id){
        Country country = countryService.findById(id);

        if(country == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(country);
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody CountryDto countryDto){
        Country country = countryService.create(countryDto);

        if(country == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(country);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id,
                                               @RequestBody CountryDto countryDto){

        Country country = countryService.edit(id, countryDto);

        if(country == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(country);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable Long id){
        Country country = countryService.findById(id);

        if(country == null)
            return ResponseEntity.notFound().build();
        else {
            countryService.delete(id);
            return ResponseEntity.ok(country);
        }
    }
}
