package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

    /**
     * WebDataBinder 에 여러 Validator 가 추가될 시,
     * 검증할 객체에 상응하는 Validator 인지 확인
     *
     * @param clazz 검증할 클래스
     * @return clazz 가 Item 의 class 와 같은지, 또는, Item 의 자식 클래스인지 여부
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Item item = (Item) target; 

        if (!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{"1,000", "10,000"}, null);

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{"9,999"}, null);
        }

        //특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{"1,000", resultPrice}, null);
            }
        }

    }
}
