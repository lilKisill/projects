package registrar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import registrar.model.Share;
import registrar.repository.ShareRepository;

@Service
public class ShareServiceImpl implements ShareService{
    private final ShareRepository shareRepository;

    @Autowired
    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public Share save(Share share) {
        return shareRepository.save(share);
    }

    @Override
    public Share getShareById(Long id) {
        Share result = shareRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public Page<Share> getAllShares(Pageable pageable) {
        return shareRepository.findAll(pageable);
    }

    @Override
    public Page<Share> getAllByStatus(String status, Pageable pageable) {
        return shareRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Share getShareByCod(long cod) {
        return shareRepository.findByCod(cod);
    }

    @Override
    public Page<Share> getAllByCod(long cod, Pageable pageable) {
        return shareRepository.findAllByCod(cod, pageable);
    }




}
