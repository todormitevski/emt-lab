package mk.ukim.finki.service.impl;

import mk.ukim.finki.model.Country;
import mk.ukim.finki.model.dto.CountryDto;
import mk.ukim.finki.model.exceptions.InvalidCountryIdException;
import mk.ukim.finki.repository.CountryRepository;
import mk.ukim.finki.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(InvalidCountryIdException::new);
    }

    @Override
    public Country create(CountryDto countryDto) {
        Country country = new Country();
        return saveCountry(countryDto, country);
    }

    @Override
    public Country edit(Long id, CountryDto countryDto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(InvalidCountryIdException::new);
        return saveCountry(countryDto, country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    private Country saveCountry(CountryDto countryDto, Country country){

        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

        return countryRepository.save(country);
    }
}
