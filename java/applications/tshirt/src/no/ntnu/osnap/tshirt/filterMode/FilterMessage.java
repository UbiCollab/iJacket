/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package no.ntnu.osnap.tshirt.filterMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import no.ntnu.osnap.tshirt.R;

/**
 * Displays what options User has with a message from a social service
 */
public class FilterMessage extends Activity implements View.OnClickListener{

    private String currentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_message);

        currentFilter = getIntent().getStringExtra(ChangeMode.CURRENT_FILTER);
        ((TextView)findViewById(R.id.fm_labelCurrentOutput)).setText(currentFilter);
        ((Button)findViewById(R.id.fm_buttonGetMessage)).setOnClickListener(this);
        ((Button)findViewById(R.id.fm_buttonGetSender)).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            setResult(RESULT_OK, data);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_buttonGetMessage:
                ChangeMode.changeActivityToCompareResult(this, currentFilter + ":" + getString(R.string.getMessage));
                break;
            case R.id.fm_buttonGetSender:
                ChangeMode.changeActivityToPerson(this, currentFilter + ":" + getString(R.string.getSender));
                break;
        }
    }

}
