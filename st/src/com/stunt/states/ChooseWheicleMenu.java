package com.stunt.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.stunt.Globals;
import com.stunt.entity.WheicleEnum;
import com.stunt.handlers.GameStateManager;

public class ChooseWheicleMenu extends GameState {
	private Stage stage;

	
	public ChooseWheicleMenu(final GameStateManager gsm, final String mapPath) {
		super(gsm);
		
		this.stage = new Stage();
		Gdx.input.setInputProcessor(this.stage);
		
		final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		final Table scrollTable = new Table();

		
		
		for( WheicleEnum we : WheicleEnum.values() )
		{
			TextButton tb = new TextButton(we.getName(), skin);
			tb.setTouchable(Touchable.enabled);
			tb.addListener(new InputListener()
				{
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer,
							int button)
					{
						if (event.getTarget() instanceof Label)
						{
							Label targetLabel = (Label) event.getTarget();
							gsm.setState(Globals.PLAY_GS,mapPath
									, WheicleEnum.getByName(targetLabel.getText().toString()));

						}

						return super.touchDown(event, x, y, pointer, button);
					}
				});
			scrollTable.add(tb);
			scrollTable.setWidth(Globals.V_WIDTH);
			scrollTable.row();
		}
		
		final ScrollPane scroller = new ScrollPane(scrollTable);
		final Table table = new Table();
		table.setFillParent(true);
		table.add(scroller).fill().expand();

		this.stage.addActor(table);
	}

	@Override
	public void handleInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render()
	{
		this.stage.act();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		this.stage.draw();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
