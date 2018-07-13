package com.example.demo.rest;

import com.example.demo.rest.dto.MadKingDto;
import com.example.demo.rest.dto.ResponseDto;
import com.example.demo.service.MadKingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/madkings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MadKingResource {

    private final MadKingService madKingService;

    @Autowired
    public MadKingResource(MadKingService madKingService) {
        this.madKingService = madKingService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAll() {
        List<MadKingDto> madKings = madKingService.getAll();
        ResponseDto<List<MadKingDto>> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(200);
        responseDto.setMessage("queried successfully");
        responseDto.setData(madKings);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping(path = "/{id}")
    public ResponseEntity<ResponseDto> getById(@PathVariable(name = "id") Long id) {
        MadKingDto madKing = madKingService.getById(id);
        ResponseDto<MadKingDto> responseDto = new ResponseDto<>();
        responseDto.setStatusCode(200);
        responseDto.setMessage("queried successfully");
        responseDto.setData(madKing);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> save(@RequestBody List<MadKingDto> madKingDtos) {
        ResponseDto<List<MadKingDto>> responseDto = new ResponseDto<>();
        List<MadKingDto> madKings = madKingService.save(madKingDtos);
        responseDto.setStatusCode(200);
        responseDto.setMessage("saved successfully");
        responseDto.setData(madKings);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDto> update(@RequestBody List<MadKingDto> madKingDtos, @RequestParam("transactional") boolean transactional) {
        ResponseDto<List<MadKingDto>> responseDto = new ResponseDto<>();
        List<MadKingDto> madKings;
        if(transactional) {
            madKings = madKingService.updateTransactional(madKingDtos);
        } else {
            madKings = madKingService.update(madKingDtos);
        }
        responseDto.setStatusCode(200);
        responseDto.setMessage("updated successfully");
        responseDto.setData(madKings);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
