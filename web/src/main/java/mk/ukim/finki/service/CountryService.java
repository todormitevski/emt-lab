package mk.ukim.finki.service;

import mk.ukim.finki.model.Country;
import mk.ukim.finki.model.dto.CountryDto;

import java.util.List;

public interface CountryService {

    List<Country> listAll();

    Country findById(Long id);

    Country create(CountryDto countryDto);

    Country edit(Long id, CountryDto countryDto);

    void delete(Long id);
}
