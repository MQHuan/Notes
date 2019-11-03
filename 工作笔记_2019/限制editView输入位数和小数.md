import android.text.InputFilter;
import android.text.Spanned;

public class InputFilter implements InputFilter {
    /**
     * 保留小数点前多少位，默认三位，既到千位
     */
    private int mDecimalStarNumber = 5;

    /**
     * 保留小数点后多少位，默认两位
     */
    private int mDecimalEndNumber = 1 ;


    public void setDecimalStarNumber(int decimalStarNumber) {
        this.mDecimalStarNumber = decimalStarNumber;
    }

    public void setDecimalEndNumber(int decimalEndNumber) {
        this.mDecimalEndNumber = decimalEndNumber;
    }

    public InputFilter(){}

    public InputFilter(int decimalStarNumber, int decimalEndNumber) {
        this.mDecimalStarNumber = decimalStarNumber;
        this.mDecimalEndNumber = decimalEndNumber;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        String lastInputContent = dest.toString();

        if (source.equals(".") && lastInputContent.length() == 0) {
            return "0.";
        }

        if (!source.equals(".") && !source.equals("") && lastInputContent.equals("0")) {
            return ".";
        }

        if (source.equals(".") && lastInputContent.contains(".")) {
            return "";
        }

        if (lastInputContent.contains(".")) { //有没有小数点
            int index = lastInputContent.indexOf(".");
            //删除操作  删除小数点  超过范围的 不让删除
            if (dend - dstart > 0 && index + 1 == dend && index >= mDecimalStarNumber && lastInputContent.length()> index  + 1) { //删除小数点
                return dest.subSequence(dstart, dend);
            }
            if (index < dend && lastInputContent.length() + source.length() - index > mDecimalEndNumber + 1) { //增加小数点后位限制
                return "";
            }
            if (index >= mDecimalStarNumber && dend <= index) { //增加小数点前限制
                return "";
            }
        } else {
            if (!source.equals(".") && lastInputContent.length() >= mDecimalStarNumber) {
                return "";
            }
        }

        return null;
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}


使用样例：
        mBinding.etHikvisionModifyEditWeight.setFilters(new InputFilter[]{ new InputFilter(4, 2)} );
