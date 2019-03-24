package com.xlong.back.controller;

import com.xlong.back.entity.Consumer;
import com.xlong.back.entity.Page;
import com.xlong.back.entity.Producter;
import com.xlong.back.entity.Request;
import com.xlong.back.repository.RequestRepository;
import com.xlong.back.repository.ResponseRepository;
import com.xlong.back.zk.ProZKClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ProZKClient zkClient;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ResponseRepository responseRepository;

    private static final String ZK_PROVIDER_ROOT = "/provider";

    private static final String ZK_CONSUMER_ROOT = "/consumer";

    @RequestMapping(value="/producters", method=RequestMethod.POST)
    public Page getProducters(@RequestParam(value = "start", defaultValue = "0") Integer start,
                              @RequestParam(value = "length", defaultValue = "10") Integer size,
                              @RequestParam("draw") int draw){
        List<String> nodes = zkClient.getChildrenNode(ZK_PROVIDER_ROOT);
        List<Producter> producters = new ArrayList<>();
        if (nodes != null) {
            logger.info(nodes.toString());
            for (String n : nodes) {
                String nodePath = ZK_PROVIDER_ROOT + "/" + n;
                String data = zkClient.getData(nodePath);
                logger.info("node data is {}", data);
                String[] tmp = {"", ""};
                if (data != null) {
                    tmp = data.split(":");
                }

                Producter p = new Producter();
                p.setId(n);
                p.setHost(tmp[0]);
                p.setPort(tmp[1]);
                p.setCreationTime(zkClient.getCreationTime(nodePath));
                producters.add(p);
            }
        }
        Page<Producter> p = new Page<>();
        p.setContent(producters);
        p.setFirst(true);
        p.setLast(true);
        p.setNumber(0);
        p.setNumberOfElements(producters.size());
        p.setTotalElements(producters.size());
        p.setTotalPages(1);
        p.setSize(size);
        return p;
    }

    @RequestMapping(value="/consumers", method=RequestMethod.POST)
    public Page getConsumers(@RequestParam(value = "start", defaultValue = "0") Integer start,
                              @RequestParam(value = "length", defaultValue = "10") Integer size,
                              @RequestParam("draw") int draw){
        List<String> nodes = zkClient.getChildrenNode(ZK_CONSUMER_ROOT);
        List<Consumer> consumers = new ArrayList<>();
        if (nodes != null) {
            logger.info(nodes.toString());
            for (String n : nodes) {
                String nodePath = ZK_CONSUMER_ROOT + "/" + n;
                String data = zkClient.getData(nodePath);
                logger.info("node data is {}", data);

                Consumer consumer = new Consumer();
                consumer.setId(n);
                consumer.setHost(data);
                consumer.setCreationTime(zkClient.getCreationTime(nodePath));
                consumers.add(consumer);
            }
        }
        Page<Consumer> p = new Page<>();
        p.setContent(consumers);
        p.setFirst(true);
        p.setLast(true);
        p.setNumber(0);
        p.setNumberOfElements(consumers.size());
        p.setTotalElements(consumers.size());
        p.setTotalPages(1);
        p.setSize(size);
        return p;
    }

    @RequestMapping(value="/node-statics", method=RequestMethod.GET)
    public Map<String, Integer> nodeStatics() {
        List<String> productorNodes = zkClient.getChildrenNode(ZK_PROVIDER_ROOT);
        List<String> consumerNodes = zkClient.getChildrenNode(ZK_CONSUMER_ROOT);
        int productorNum = productorNodes.size();
        int consumerNum = consumerNodes.size();
        Map<String, Integer> statics_num = new HashMap<>();
        statics_num.put("productors", productorNum);
        statics_num.put("consumers", consumerNum);
        logger.info("Productors : {}, Consumers : {}", productorNum, consumerNum);
        return statics_num;
    }

    @RequestMapping(value="/requests", method=RequestMethod.POST)
    public org.springframework.data.domain.Page getRequests(@RequestParam(value = "start", defaultValue = "0") Integer start,
                             @RequestParam(value = "length", defaultValue = "10") Integer size,
                             @RequestParam("draw") int draw){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Integer page = start/size;
        Pageable pageable = new PageRequest(page, size, sort);
        return requestRepository.findAll(pageable);
    }

    @RequestMapping(value="/responses", method=RequestMethod.POST)
    public org.springframework.data.domain.Page getResponses(@RequestParam(value = "start", defaultValue = "0") Integer start,
                            @RequestParam(value = "length", defaultValue = "10") Integer size,
                            @RequestParam("draw") int draw){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Integer page = start/size;
        Pageable pageable = new PageRequest(page, size, sort);
        return responseRepository.findAll(pageable);
    }

    @RequestMapping(value="/service-statics", method=RequestMethod.GET)
    public Map<String, List<Integer>> serviceStatics(@RequestParam(value = "year") Integer year) {
        Map<String, List<Integer>> map = new HashMap<>();
        // 查询每个月的个数
        List<Integer> requestsNum = new ArrayList<>(12);
        StringBuilder logNum = new StringBuilder(24);
        for (Integer month = 1; month <= 12; month++) {
             Integer num = requestRepository.getCountByYearAndMonth(year, month);
             requestsNum.add(num);
             logNum.append(num + " ");
        }

        logger.info("request count: {}", logNum.toString());
        List<Integer> responseNum = new ArrayList<>(12);
        logNum = new StringBuilder(24);
        for (Integer month = 1; month <= 12; month++) {
            Integer num = responseRepository.getCountByYearAndMonth(year, month);
            responseNum.add(num);
            logNum.append(num + " ");
        }
        logger.info("response count: {}", logNum.toString());
        map.put("requests", requestsNum);
        map.put("responses", responseNum);
        return map;
    }

    @RequestMapping(value="/service-time-statics", method=RequestMethod.GET)
    public Map<String, List<Integer>> serviceTimeStatics(@RequestParam(value = "year") Integer year,
                                                         @RequestParam(value = "month") Integer month) {
        return null;
    }
}
