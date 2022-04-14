package com.paysonix.httprequesthandler;

import com.paysonix.httprequesthandler.domain.Response;
import com.paysonix.httprequesthandler.domain.Result;
import com.paysonix.httprequesthandler.service.HashPresentationServiceImpl;
import com.paysonix.httprequesthandler.service.ParamsConcatenationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Sergei Korepanov
 */
@RestController
@RequestMapping("/operations")
public class PostRequestController {
    private static final Logger logger = LoggerFactory.getLogger(PostRequestController.class);

    public static final String SUCCESS_STATUS = "success";
    private final HashPresentationServiceImpl hashCalculationService;
    private final ParamsConcatenationService paramsConcatenationService;

    public PostRequestController(HashPresentationServiceImpl hashCalculationService,
                                 ParamsConcatenationService paramsConcatenationService) {
        this.hashCalculationService = hashCalculationService;
        this.paramsConcatenationService = paramsConcatenationService;
    }

    @PostMapping(value = "/post", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response handlePostRequest(@RequestParam long operationId,
                                      @RequestBody Map<String, String> params) {

        String sortedParams = paramsConcatenationService.concatParams(params);
        String signatureValue = hashCalculationService.presentParamsHashAsString(sortedParams);
        logger.info("Sending response");
        return new Response(SUCCESS_STATUS, List.of(new Result(signatureValue)));
    }
}
