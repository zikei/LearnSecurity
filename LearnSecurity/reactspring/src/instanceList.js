/**
 * インスタンス一覧画面
 */
import React from 'react';
import './instanceList.css';
import { Header } from './components/Header';
import reportWebVitals from './reportWebVitals';

export  default  function InstanceList() {
  return (
    <>
      <Head>
        <title>LearnSecurity - インスタンス一覧</title>
        <meta name="description" content="Generated by create next app" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <Header />
      </Head>
    </>
  )
}

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals]

reportWebVitals();