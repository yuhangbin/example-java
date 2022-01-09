package base.reference.string;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yuhangbin
 * @date 2022/1/5
 **/
@Data
public class UseStaticConstant {

	public static final String V = Constant.value;

	public UseStaticConstant() {
		System.out.println();
	}

	public String getV() {
		return V;
	}
}
