package com.example.demo.controller.v2

import com.example.demo.data.Request
import com.example.demo.data.Response
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("v2")
class TestController2 {

    @GetMapping("test")
    suspend fun testGet(
        @RequestHeader headers: HttpHeaders,
    ): Response {
        return Response("ok")
    }

    @PostMapping("test")
    suspend fun testPost(
        @RequestHeader headers: HttpHeaders,
        @RequestBody request: Request
    ): Response {
        return Response("ok")
    }
}