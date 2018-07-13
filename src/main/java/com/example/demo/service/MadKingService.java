package com.example.demo.service;

import com.example.demo.entity.MadKingEntity;
import com.example.demo.repo.MadKingRepository;
import com.example.demo.rest.dto.MadKingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MadKingService {

    private MadKingRepository madKingRepository;

    @Autowired
    public MadKingService(MadKingRepository madKingRepository) {
        this.madKingRepository = madKingRepository;
    }

    @Transactional(rollbackOn = {}, dontRollbackOn = {})
    public List<MadKingDto> save(List<MadKingDto> madKingDtos) {
        List<MadKingEntity> madKings = new ArrayList<>(madKingDtos.size());
        for(MadKingDto madKingDto: madKingDtos) {
            madKings.add(fromDto(madKingDto));
        }
        madKingRepository.saveAll(madKings);

        madKingDtos.clear();
        for(MadKingEntity madKingEntity: madKings) {
            madKingDtos.add(fromEntity(madKingEntity));
        }
        return madKingDtos;
    }

    @Transactional
    public List<MadKingDto> updateTransactional(List<MadKingDto> madKingDtos) {
        for (MadKingDto madKingDto : madKingDtos) {
            madKingRepository.save(fromDto(madKingDto));
        }
        return madKingDtos;
    }

    public List<MadKingDto> update(List<MadKingDto> madKingDtos) {
        try {
            for (MadKingDto madKingDto : madKingDtos) {
                madKingRepository.save(fromDto(madKingDto));
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            //ignore
        }
        return madKingDtos;
    }


    public List<MadKingDto> getAll() {
        Iterable<MadKingEntity> madKingEntities = madKingRepository.findAll();

        List<MadKingDto> madKingDtos = new ArrayList<>();
        for(MadKingEntity madKingEntity: madKingEntities) {
            madKingDtos.add(fromEntity(madKingEntity));
        }
        return madKingDtos;
    }

    public MadKingDto getById(Long id) {
        Optional<MadKingEntity> madKingEntityOptional = madKingRepository.findById(id);

        return fromEntity(madKingEntityOptional.orElseThrow(()-> new RuntimeException("madking not found")));
    }

    private MadKingEntity fromDto(MadKingDto madKingDto) {
        MadKingEntity madKingEntity = new MadKingEntity();
        madKingEntity.setId(madKingDto.getId());
        madKingEntity.setFirstName(madKingDto.getFirstName());
        madKingEntity.setLastName(madKingDto.getLastName());
        madKingEntity.setKingdom(madKingDto.getKingdom());
        madKingEntity.setSwornOn(madKingDto.getSwornOn());
        return madKingEntity;
    }

    private MadKingDto fromEntity(MadKingEntity madKingEntity) {
       MadKingDto madKingDto = new MadKingDto();
       madKingDto.setId(madKingEntity.getId());
       madKingDto.setFirstName(madKingEntity.getFirstName());
       madKingDto.setLastName(madKingEntity.getLastName());
       madKingDto.setKingdom(madKingEntity.getKingdom());
       madKingDto.setSwornOn(madKingEntity.getSwornOn());
       return madKingDto;

    }

}
