package com.module3.member.controller;

import com.module3.member.model.Member;
import com.module3.member.service.Memberservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberContriller {

    private final Memberservice memberservice;

    @CrossOrigin
    @PostMapping("/add")
    public ResponseEntity<?> addmember(@RequestBody Member member){
        return new ResponseEntity<>(memberservice.addMember(member) ,HttpStatus.OK);

    }

    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editmember(@PathVariable Long id, @RequestBody Member member){
        return new ResponseEntity<>(memberservice.editMember(id, member) ,HttpStatus.OK);

    }

    @CrossOrigin
    @GetMapping("/getAll")
    public ResponseEntity<?> findAllMember(){
        return new ResponseEntity<>(memberservice.findAllMember(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(memberservice.findMemberById(id), HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletemember(@PathVariable Long id){
        if(memberservice.deleteMember(id) != 1){
            return new ResponseEntity<>(memberservice.deleteMember(id), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(memberservice.deleteMember(id), HttpStatus.OK);
        }
    }
}
