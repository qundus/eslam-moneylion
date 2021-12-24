package eslam.moneylion.assessment.controllers;

import java.util.AbstractMap;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eslam.moneylion.assessment.services.FeatureService;
import eslam.moneylion.assessment.services.UserService;

@RestController
@RequestMapping(path = "/api/feature")
public class FeatureController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private FeatureService featureService;

    @GetMapping
    public ResponseEntity<Object> canUserAccess(@RequestParam("email") String email, @RequestParam("featureName") String featureName) {
        boolean result = userService.hasFeature(email, featureName);
        return new ResponseEntity<>(new AbstractMap.SimpleEntry<>("canAccess", result), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> setFeatureOnUser(@RequestBody HashMap<String, Object> obj)  {
        try {
            if (obj == null || obj.size() <= 0) throw new Exception("request body can't be null or empty");
            String featureName = (String)obj.get("featureName");
            String email = (String)obj.get("email");
            Boolean enable = (Boolean)obj.get("enable");

            // cases not to add feature
            if (!enable) throw new Exception("not adding feature to user set");
            if (!featureService.exists(featureName)) throw new Exception("feature does not exist");
            if (!userService.exists(email)) throw new Exception("feature does not exist");

            // add feature to use set only if enabled
            userService.addFeatureToUser(email, featureName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }
}