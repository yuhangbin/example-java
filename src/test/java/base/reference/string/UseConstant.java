package base.reference.string;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuhangbin
 * @date 2022/1/5
 **/
@Data
public class UseConstant {

	public String V = Constant.value;

	public UseConstant() {
		System.out.println();
	}

	public String getV() {
		return V;
	}
}
