package forms;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import play.data.validation.Constraints.Required;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "amount" }))
public class PriceForm implements Serializable{
	
	@Required
	public Integer amount;
}
