package org.spring.springboot.controller.host;

import org.spring.springboot.domain.Host;
import org.spring.springboot.domain.ResponseBean;
import org.spring.springboot.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HostUserController {

    @Autowired
    private HostService hostService;

    @RequestMapping(value = "/house/user/register", method = RequestMethod.POST)
    public ResponseBean registerOneUser(@RequestBody Host host) {
        return hostService.saveHost(host);
    }

    @RequestMapping(value = "/house/user/loginIn", method = RequestMethod.POST)
    public ResponseBean HostLoginIn(@RequestBody Host host) {
        return hostService.login(host);
    }

    @RequestMapping(value = "/house/publish/createHouse", method = RequestMethod.POST)
    public ResponseBean createHouse(@RequestBody Host host) {
        return hostService.createOneHouse(host);
    }

    @RequestMapping(value = "/house/publish/saveHouseDescription", method = RequestMethod.POST)
    public ResponseBean saveHouseDescription(@RequestBody String params) {
        System.out.print(params);
        return hostService.saveDescription(params);
    }
}
