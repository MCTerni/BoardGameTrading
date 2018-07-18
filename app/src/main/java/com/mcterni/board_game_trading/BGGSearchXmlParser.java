package com.mcterni.board_game_trading;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BGGSearchXmlParser {
    // We don't use namespaces
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }
    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "boardgames");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            // Starts by looking for the entry tag
            if (tagName.equals("boardgame")) {
                entries.add(readBoardgame(parser));
            } else {
                skip(parser);
            }
        }
        return entries;
    }


    public static class GameDetails {
        public final String gameId;
        public final String gameName;

        private GameDetails(String name, String id) {
            gameName = name;
            gameId = id;
        }


    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
    private GameDetails readBoardgame(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "boardgame");
        String id = parser.getAttributeValue(0);//readId(parser);
        String name = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tagName = parser.getName();
            if (tagName.equals("name")) {
                name = readGameName(parser);
            } else if (tagName.equals("boardgame")) {
                id = readId(parser);
            } else {
                skip(parser);
            }
        }
        return new GameDetails(name, id);
    }

    // Processes title tags in the feed.
    private String readGameName(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "name");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, "name");
        return title;
    }

    // Processes link tags in the feed.
    private String readId(XmlPullParser parser) throws IOException, XmlPullParserException {
        String id = null;
        parser.require(XmlPullParser.START_TAG, ns, "boardgame");


        id = parser.getAttributeValue(0);
        parser.nextTag();

        parser.require(XmlPullParser.END_TAG, ns, "boardgame");
        return id;
    }


    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
        }
        return result;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
