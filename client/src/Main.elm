module Main exposing (Msg(..), main, update, view)

import Browser
import Html exposing (Html, button, div, text)
import Html.Events exposing (onClick)


main =
    Browser.sandbox { init = 0, update = update, view = view }


type Msg
    = Increment
    | Decrement
    | Reset


update msg model =
    case msg of
        Increment ->
            model + 1

        Decrement ->
            model - 1

        Reset ->
            0


view model =
    div []
        [ button [ onClick Increment ] [ text "+" ]
        , div [] [ text (String.fromInt model) ]
        , button [ onClick Reset ] [ text "Reset" ]
        ]


type alias Request =
    { leftHandSide : String
    , rightHandSide : String
    , language : String
    , exclude_tokens : List String
    }


type alias Response =
    { match : String
    , replace : String
    , matches : List Entry
    }


type alias Entry =
    { symbolic : Symbolic
    , concrete : String
    }


type alias Symbolic =
    { type_ : String
    , name : String
    , text : String
    }
