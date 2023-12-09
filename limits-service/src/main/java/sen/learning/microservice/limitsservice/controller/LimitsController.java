package sen.learning.microservice.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sen.learning.microservice.limitsservice.bean.Limits;
import sen.learning.microservice.limitsservice.config.Config;

@RestController
public class LimitsController {

    @Autowired
    private Config config;

    @GetMapping("limits")
    public Limits retrieveLimits() {
        return new Limits(config.getMinimum(), config.getMaximum());
    }
}
