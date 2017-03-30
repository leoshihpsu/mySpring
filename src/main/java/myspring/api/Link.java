package myspring.api;

import org.springframework.web.bind.annotation.*;

@RestController
public class Link {
    @RequestMapping(value="/link/{leon}", method = RequestMethod.GET)
    public String look(@PathVariable(value="leon") String search){
        return "<a href= https://www.google.pl/search?site=&source=hp&q=" + search +">click</a>";
    }
}
