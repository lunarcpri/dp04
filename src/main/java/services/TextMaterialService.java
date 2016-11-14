package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.TextMaterialRepository;

@Service
@Transactional
public class TextMaterialService {

    @Autowired
    private TextMaterialRepository textMaterialRepository;

    public TextMaterialService(){
        super();
    }
}
