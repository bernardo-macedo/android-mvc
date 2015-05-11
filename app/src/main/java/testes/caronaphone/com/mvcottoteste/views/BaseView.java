package testes.caronaphone.com.mvcottoteste.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import testes.caronaphone.com.mvcottoteste.events.SingletonBus;
import testes.caronaphone.com.mvcottoteste.exceptions.ViewException;

/**
 * Created by -Bernardo on 2015-05-11.
 */
public abstract class BaseView extends LinearLayout {

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        SingletonBus.getInstance().register(this);
    }

    /**
     * Remove the listener from the model
     */
    public void destroy() {
        SingletonBus.getInstance().unregister(this);
    }

    public abstract void handleError(ViewException e);
}
