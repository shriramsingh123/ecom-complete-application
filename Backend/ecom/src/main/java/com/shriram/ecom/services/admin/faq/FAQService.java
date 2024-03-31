package com.shriram.ecom.services.admin.faq;

import com.shriram.ecom.dto.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
