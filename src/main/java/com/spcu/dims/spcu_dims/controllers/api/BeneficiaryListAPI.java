//package com.spcu.dims.spcu_dims.controllers.api;
//
//import com.spcu.dims.spcu_dims.entities.BeneficiaryList;
//import com.spcu.dims.spcu_dims.services.BeneficiaryListServices;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
//import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/spcu-dims/api/v1/beneficiary_list")
//@AllArgsConstructor
//public class BeneficiaryListAPI {
//
//    private final BeneficiaryListServices beneficiaryListServices;
//    private static final String NOT_FOUND = "ID Not Found.";
//
//    @PostMapping("/create")
//    public ResponseEntity<Object> beneficiaryListCreate(@RequestParam("name") String name, @RequestParam ("zone")String diocese, @RequestParam ("township")String township) {
//        Object beneficiaryList = beneficiaryListServices.createBeneficiaryList(name, diocese, township);
//        if (beneficiaryList instanceof String) {
//
//            return new ResponseEntity<>(beneficiaryList, HttpStatus.CONFLICT);
//        } else if (beneficiaryList != null) {
//            // Continue with the original logic when archdiocese is not a String
//            return new ResponseEntity<>(beneficiaryList, HttpStatus.CREATED);
//        } else {
//            // Handle the case where archdiocese is null
//            String errorMessage = "Failed to create BeneficiaryList with the name '" + name + "'.";
//            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/show/table")
//    public DataTablesOutput<BeneficiaryList> beneficiaryList(@Valid @RequestBody DataTablesInput input){
//        return beneficiaryListServices.tableBeneficiaryList(input);
//    }
//
//    @PatchMapping("/delete/{id}")
//    public ResponseEntity<Object> delete(@PathVariable("id") int id) {
//        BeneficiaryList beneficiaryList = beneficiaryListServices.delete(id);
//        if (beneficiaryList != null) {
//            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
//    }
//
//    @PatchMapping("/update")
//    public ResponseEntity<Object> update(@RequestParam("id") int id, @RequestParam ("name") String name, @RequestParam("zone") String zone, @RequestParam("township") String township) {
//        Object beneficiaryList = beneficiaryListServices.update(id, name, zone, township);
//        if(beneficiaryList!= null){
//            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping("/show/all")
//    public ResponseEntity<List<String>> beneficiaryListShowAll() {
//        return new ResponseEntity<>(beneficiaryListServices.showAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/show/{id}")
//    public ResponseEntity<Object> show(@PathVariable("id") int id) {
//        BeneficiaryList beneficiaryList = beneficiaryListServices.findById(id);
//        if(beneficiaryList != null){
//            return new ResponseEntity<>(beneficiaryList, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(NOT_FOUND, HttpStatus.NOT_FOUND);
//    }
//
//}
