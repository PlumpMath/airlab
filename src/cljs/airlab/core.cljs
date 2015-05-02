(ns airlab.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [cljs.core.async :refer [put! chan <!]]
   [clojure.data :as data]
   [clojure.string :as string]
   [om-tools.core :refer-macros [defcomponent]]
   [om.core :as om :include-macros true]
   [om.dom :as dom :include-macros true]
   [sablono.core :as h :refer-macros [ html ]]
   ))


(defonce app-state
  (atom
    {:aircraft
     [{:icao "AE094B" :reg "85-1234" :type "U2" :alt 60000
       :lat 31.4 :lon -138.4 :speed 438 :squawk nil}
      {:icao "AC916F" :reg "N520PD" :type "AS50" :alt 800
       :lat 31.9 :lon -138.2 :speed 80 :squawk "1200"}]}))


(defcomponent aircraft-list-view [aircraft owner]
  (render [_]
    (html
     [:tr
      [:td (:icao aircraft)]
      [:td (:reg aircraft)]
      [:td (:type aircraft)]
      [:td (:alt aircraft)]
      [:td (:speed aircraft)]
      [:td (:squawk aircraft)]
      [:td (:distance aircraft)]
      [:td (:signal aircraft)]])))


(defcomponent aircrafts-list-view [app owner]
  (render [_]
    (let [aircrafts (:aircraft app)]
      (html
       [:div {:class "aircraft-list"}
        [:h1 "Aircraft"]
        [:table
         [:thead
          [:tr
           [:th "ICAO"]
           [:th "Reg"]
           [:th "Type"]
           [:th "Alt"]
           [:th "Spd"]
           [:th "Sqk"]
           [:th "Dist"]
           [:th "Sig"]]]
         (om/build-all
          aircraft-list-view aircrafts)]]))))



(defn main []
  (om/root
   aircrafts-list-view
   app-state
   {:target (. js/document (getElementById "app"))}))
