package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private final String port;
    private final String memoryLimit;
    private final String cfInstanceIndex;
    private final String cfInstanceAddr;

    public EnvController(@Value("${port:80}") String port, @Value("${memory.limit:12G}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
                         @Value("${cf.instance.addr:some address}") String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> env = new HashMap<>();
        env.put("PORT", this.port);
        env.put("MEMORY_LIMIT", this.memoryLimit);
        env.put("CF_INSTANCE_INDEX", this.cfInstanceIndex);
        env.put("CF_INSTANCE_ADDR", this.cfInstanceAddr);

        return env;
    }
}
