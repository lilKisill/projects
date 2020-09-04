package registrar.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import registrar.model.Share;

public interface ShareService {
    Share save(Share task);
    Share getShareById(Long id);
    Page<Share> getAllShares(Pageable pageable);
    Page<Share> getAllByCod(long cod, Pageable pageable);
    Page<Share> getAllByStatus(String status, Pageable pageable);
    Share getShareByCod(long cod);
}
