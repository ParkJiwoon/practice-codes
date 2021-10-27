import React from "react";
import PropTypes from 'prop-types'

const MyComponent = ({ name, number, children }) => {
  children = 'hello'
  return <div>이름: {name}, children: {children}, number: {number}</div>
}

MyComponent.defaultProps = {
  name: '기본 이름'
}

MyComponent.propTypes = {
  name: PropTypes.string,
  number: PropTypes.number.isRequired
}

export default MyComponent;