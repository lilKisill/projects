package registrar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import registrar.model.History;
import registrar.model.Share;
import registrar.service.HistoryService;
import registrar.service.ShareService;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "/main")
@CrossOrigin(origins = "http://localhost:8080")
public class MainController {
    private final ShareService shareService;
    private final HistoryService historyService;

    @Autowired
    public MainController(ShareService shareService, HistoryService historyService) {
        this.shareService = shareService;
        this.historyService = historyService;
    }

    @GetMapping("/shares")
    public ResponseEntity<Page<Share>> getAllShare(@RequestParam(defaultValue = "") String filter,
                                                   @RequestParam(defaultValue = "") String action,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Share> listOfShares = null;
            if(filter.isEmpty()){
                listOfShares = shareService.getAllShares(PageRequest.of(page, size));
            } else if(filter.equals("status")){
                listOfShares = shareService.getAllByStatus(action, PageRequest.of(page, size));
            } else if(filter.equals("cod")){
                listOfShares = shareService.getAllByCod(Long.parseLong(action), PageRequest.of(page, size));
            }

            if (listOfShares != null && listOfShares.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listOfShares, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Share> createShare(@RequestBody Share shareRequest) {
        try {
            if (shareRequest == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            Share shareFromDb = shareService.getShareByCod(shareRequest.getCod());
            if (shareFromDb != null) {
                return new ResponseEntity<>(shareFromDb, HttpStatus.NOT_ACCEPTABLE);
            }
            Share share = new Share();
            share.setCod(shareRequest.getCod());
            share.setComment(shareRequest.getComment());
            share.setAmount(shareRequest.getAmount());
            share.setValue(shareRequest.getValue());
            share.setTotalValue(shareRequest.getValue() * shareRequest.getAmount());
            share.setReleaseDate(LocalDate.now());
            share.setStatus("active");
            shareService.save(share);
            return new ResponseEntity<>(share, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/put/{cod}")
    public ResponseEntity<Share> updateShare(@PathVariable("cod") Long cod, @RequestBody Share shareRequest) {
        Share changeShare = shareService.getShareByCod(cod);
        if (changeShare == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        History log = new History();
        log.setLog(changeShare.getCod() + " - comment: " + changeShare.getComment() + ", " + shareRequest.getComment() + "; " +
                "amount: " + changeShare.getAmount() + ", " + shareRequest.getAmount() + "; " +
                "value: " + changeShare.getValue() + ", " + shareRequest.getValue() + "; " +
                "totalValue: " + changeShare.getTotalValue() + ", " + shareRequest.getValue() * shareRequest.getAmount() + ".");
        changeShare.setComment(shareRequest.getComment());
        changeShare.setAmount(shareRequest.getAmount());
        changeShare.setValue(shareRequest.getValue());
        changeShare.setTotalValue(shareRequest.getValue() * shareRequest.getAmount());
        shareService.save(changeShare);
        historyService.save(log);
        return new ResponseEntity<>(changeShare, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cod}")
    public ResponseEntity<Share> deleteShare(@PathVariable("cod") Long cod) {
        try {
            Share changeShare = shareService.getShareByCod(cod);
            if (changeShare == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            History log = new History();
            log.setLog(changeShare.getCod() + " - status: " + changeShare.getStatus() + ", deleted.");
            changeShare.setStatus("deleted");
            shareService.save(changeShare);
            historyService.save(log);
            return new ResponseEntity<>(changeShare, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
