
package com.stunt.states;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
import com.stunt.handlers.GameStateManager;

public class ChooseLevelMenu extends GameState
{
	private Map<String, String> maps = new HashMap<String, String>();
	private Stage stage;

	public ChooseLevelMenu(final GameStateManager gsm)
	{
		super(gsm);

		maps = loadAvailableMapPaths();
		this.stage = new Stage();
		Gdx.input.setInputProcessor(this.stage);

		final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		final Table scrollTable = new Table();
		for (Entry<String, String> map : maps.entrySet())
		{
			TextButton tb = new TextButton(map.getKey(), skin);
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
							gsm.setState(Globals.PLAY_GS,
									maps.get(targetLabel.getText().toString()));

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

	private Map<String, String> loadAvailableMapPaths()
	{
		Map<String, String> maps = new HashMap<String, String>();
		FileHandle dirHandle = null;
		if (Gdx.app.getType() == ApplicationType.Android)
		{
			dirHandle = Gdx.files.internal("res/maps/");
		}
		else
		{
			// ApplicationType.Desktop ..
			dirHandle = Gdx.files.internal("./bin/res/maps/");
		}

		FileHandle[] listOfFiles = dirHandle.list();
		for (int i = 0; i < listOfFiles.length; i++)
		{
			if (listOfFiles[i].file().getName().endsWith(".tmx"))
			{
				maps.put(listOfFiles[i].file().getName().replace(".tmx", ""),
						"res/maps" + "/" + listOfFiles[i].file().getName());
			}

			/*
			 * else if (listOfFiles[i].isDirectory()) {
			 * System.out.println("Directory " + listOfFiles[i].getName()); }
			 */
		}
		
		return maps;
	}

	@Override
	public void handleInput()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void update(float dt)
	{
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
	public void dispose()
	{
		// TODO Auto-generated method stub
	}
}
