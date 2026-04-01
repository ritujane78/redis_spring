package com.jane.redisspring.fib;

import com.jane.redisspring.fib.service.FibService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fib")
@AllArgsConstructor
public class FibController {
    private FibService fibService;

    @GetMapping("/{index}/{name}")
    public Mono<Integer> getFib(@PathVariable int index, @PathVariable String name) {
        return Mono.fromSupplier(() -> fibService.getFib(index, name));
    }
}
